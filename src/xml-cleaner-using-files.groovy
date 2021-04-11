#!/usr/bin/env groovy

if (args.length <2 ) {
    println "Usage: ./xml-cleaner-using-files.groovy inputfile.xml outputfile.xml"
    System.exit(0)
}

def inputFileName = args[0]
def outputFileName = args[1]

def xmlFile = getClass().getResourceAsStream(inputFileName)
def articles = new XmlParser().parse(xmlFile)

articles
    .findAll { it.author.'@id'.text() == "3" }
    .each { articles.remove(it) }

new File(outputFileName).withWriter {
    def printer = new XmlNodePrinter(new PrintWriter(it))
    printer.preserveWhitespace = true
    printer.print(articles)
}
