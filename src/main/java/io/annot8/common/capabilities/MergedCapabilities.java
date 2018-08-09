package io.annot8.common.capabilities;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.components.Resource;
import io.annot8.core.data.Content;

public class MergedCapabilities implements Capabilities {
	
	private final Capabilities[] capabilities;

	public MergedCapabilities(Capabilities...capabilities) {
		this.capabilities = capabilities;	
	}

	@Override
	public Stream<String> getRequiredAnnotations() {
		return merge(Capabilities::getRequiredAnnotations);
	}

	@Override
	public Stream<String> getOptionalAnnotations() {
		return merge(Capabilities::getOptionalAnnotations);
	}
	
	@Override
	public Stream<String> getRequiredGroups() {
		return merge(Capabilities::getRequiredGroups);
	}

	@Override
	public Stream<String> getOptionalGroups() {
		return merge(Capabilities::getOptionalGroups);
	}

	@Override
	public Stream<String> getCreatedAnnotations() {
		return merge(Capabilities::getOptionalAnnotations);
	}
	
	@Override
	public Stream<String> getCreatedGroups() {
		return merge(Capabilities::getCreatedGroups);
	}

	@Override
	public Stream<Class<? extends Content<?>>> getCreatedContent() {
		return merge(Capabilities::getCreatedContent);

	}

	@Override
	public Stream<Class<? extends Content<?>>> getRequiredContent() {
		return merge(Capabilities::getRequiredContent);

	}

	@Override
	public Stream<Class<? extends Resource>> getUsedResources() {
		return merge(Capabilities::getUsedResources);

	}

	@Override
	public Stream<Class<? extends Bounds>> getCreatedBounds() {
		return merge(Capabilities::getCreatedBounds);

	}

	private <T> Stream<T> merge(Function<Capabilities, Stream<T>> extractor) {
		if(capabilities == null || capabilities.length == 0) {
			return Stream.empty();
		}
		
		return Arrays.stream(capabilities)
				.filter(Objects::nonNull)
				.flatMap(extractor)
				.filter(Objects::nonNull)
				.distinct();
	}
}
