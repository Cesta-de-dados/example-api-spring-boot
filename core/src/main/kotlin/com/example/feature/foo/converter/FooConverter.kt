package com.example.feature.foo.converter

import com.example.foo.model.jooq.tables.records.FooRecord
import com.example.model.Foo
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class FooConverter : Converter<FooRecord, Foo> {
    override fun convert(source: FooRecord): Foo = Foo(
        id = source.id,
        name = source.name
    )
}
