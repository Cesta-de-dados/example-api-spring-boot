package com.example.feature.foo.repository.impl

import com.example.feature.foo.converter.FooConverter
import com.example.feature.foo.repository.FooRepository
import com.example.foo.model.jooq.tables.Foo.FOO
import com.example.foo.model.jooq.tables.records.FooRecord
import com.example.model.Foo
import org.jooq.Record
import org.jooq.impl.DefaultDSLContext
import org.springframework.stereotype.Repository

@Repository
class FooRepositoryImpl(
    private val dsl: DefaultDSLContext,
    private val converter: FooConverter
) : FooRepository {
    override fun list(): List<Foo> =
        dsl.selectFrom(FOO)
            .fetch(this::toModel)

    private fun toModel(record: Record): Foo =
        converter.convert(record.into(FooRecord::class.java))
}