#!/usr/bin/env groovy

// Read XML file from standard input
def articles = new XmlParser().parse(System.in)

// Remove matching tags
articles
    .findAll { it.author.'@id'.text() == "3" }
    .each { articles.remove(it) }

// Print to standard output (XmlNodePrinter prints to stdout by default)
def printer = new XmlNodePrinter()
printer.preserveWhitespace = true
printer.print(articles)
