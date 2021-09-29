package com.example.factory

import com.example.model.Foo

class FooFactory {
    companion object {
        fun createValid(): Foo = Foo(
            id = 1,
            name = "foo 1"
        )
    }
}