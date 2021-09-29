package com.example.feature.foo.controller

import com.example.factory.FooFactory
import com.example.feature.foo.service.FooService
import com.example.model.Foo
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk

class FooControllerSpec : BehaviorSpec({

    Given("a api that handle foo") {
        val service: FooService = mockk()
        val controller = spyk(
            FooController(service)
        )

        val validFoo = FooFactory.createValid()

        every { service.listFoo() } returns listOf(validFoo)

        When("a list of foo is requested") {
            Then("should list of foo with correct model") {
                val listFoo: List<Foo> = controller.listFoo()
                listFoo.first() shouldBe validFoo
            }
        }
    }
})