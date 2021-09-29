package com.example.feature.foo.service

import com.example.factory.FooFactory
import com.example.feature.foo.controller.FooController
import com.example.feature.foo.repository.FooRepository
import com.example.feature.foo.service.impl.FooServiceImpl
import com.example.model.Foo
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk

class FooServiceSpec : BehaviorSpec({
    isolationMode = IsolationMode.InstancePerTest

    Given("a service that handle foo") {
        val repository: FooRepository = mockk()
        val service = spyk(
            FooServiceImpl(repository)
        )

        val validFoo = FooFactory.createValid()

        every { repository.list() } returns listOf(validFoo)

        When("a list of foo is requested") {
            Then("should list of foo with correct model") {
                val listFoo: List<Foo> = service.listFoo()
                listFoo.first() shouldBe validFoo
            }
        }
    }
})