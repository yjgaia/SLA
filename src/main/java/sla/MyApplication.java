package sla;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import sla.resource.HelloWorldResource;

public class MyApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		// register root resource
		classes.add(HelloWorldResource.class);
		return classes;
	}
}