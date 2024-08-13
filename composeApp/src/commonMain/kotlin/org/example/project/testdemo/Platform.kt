package org.example.project.testdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform