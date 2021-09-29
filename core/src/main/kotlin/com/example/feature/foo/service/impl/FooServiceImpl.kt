package com.example.feature.foo.service.impl

import com.example.feature.foo.repository.FooRepository
import com.example.feature.foo.service.FooService
import com.example.model.Foo
import org.springframework.stereotype.Service

@Service
class FooServiceImpl(private val repository: FooRepository) : FooService {
    override fun listFoo(): List<Foo> = repository.list()
}