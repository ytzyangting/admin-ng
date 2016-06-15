package org.dstrelec.core.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;

public interface ResourceConverter<S, T> extends Converter<S, T> {

	default List<T> convertList(List<S> sourceList) {
		return sourceList.stream()
				.map(source -> convert(source))
				.collect(Collectors.toList());
	}
	
}
