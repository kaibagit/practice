package com.luliru.practice.groovy.demo

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

def json = new JsonBuilder()
json.state{
    capital "Denver"
    majorCities "Denver", "Colorado Springs", "Fort Collins"
}
println json
println JsonOutput.prettyPrint(json.toString()) //格式化输出


// sql解析
def jsonStr = '''
{
    "state": {
        "capital": "Denver",
        "majorCities": [
            "Denver",
            "Colorado Springs",
            "Fort Collins"
        ]
    }
}
'''
def slurper = new JsonSlurper()
def states = slurper.parseText(jsonStr)
println states.state.majorCities
