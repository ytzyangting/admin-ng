package org.strela.core.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public interface ResourceConverter<S, T> extends Converter<S, T> {

	default List<T> convertList(Iterable<S> iterable) {
		List<T> list = new ArrayList<>();
		iterable.forEach(source -> list.add(convert(source)));
		return list;
	}
	
	default Page<T> convertPage(Page<S> page) {
		List<T> content = convertList(page);
		PageRequest pageable = new PageRequest(page.getNumber(), page.getSize(), page.getSort());
		long total = page.getTotalElements();
		
		return new PageImpl<>(content, pageable, total);
	}
	
}
