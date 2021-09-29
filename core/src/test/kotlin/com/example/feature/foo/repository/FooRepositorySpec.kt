package com.example.feature.foo.repository

import com.example.factory.FooFactory
import com.example.feature.foo.converter.FooConverter
import com.example.feature.foo.repository.impl.FooRepositoryImpl
import com.example.feature.foo.service.impl.FooServiceImpl
import com.example.foo.model.jooq.Tables.FOO
import com.example.foo.model.jooq.tables.records.FooRecord
import com.example.model.Foo
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.jooq.RecordMapper
import org.jooq.impl.DefaultDSLContext

class FooRepositorySpec : BehaviorSpec({
    isolationMode = IsolationMode.InstancePerTest

    Given("a repository that handle foo") {
        val converter: FooConverter = mockk()
        val dsl: DefaultDSLContext = mockk()
        val repository = spyk(
            FooRepositoryImpl(dsl, converter)
        )

        val validFoo = FooFactory.createValid()

        every {
            dsl.selectFrom(FOO)
                .fetch(any<RecordMapper<FooRecord, Foo>>())
        } returns listOf(validFoo)

        When("a list of foo is requested") {
            Then("should list of foo with correct model") {
                val listFoo: List<Foo> = repository.list()
                listFoo.first() shouldBe validFoo
            }
        }
    }
})