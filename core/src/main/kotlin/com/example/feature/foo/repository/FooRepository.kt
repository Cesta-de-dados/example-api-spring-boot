package com.example.feature.foo.repository

import com.example.model.Foo

interface FooRepository {
    fun list(): List<Foo>

}
