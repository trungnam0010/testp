package com.sutrix.demo.core.utils;

import java.util.Collections;
import java.util.Map;
 
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public final class ResourceResolverUtil {
	 private ResourceResolverUtil() {
		 
	    }
	 
	    /**
	     * System user mapping which has access to read content.
	     */
	    public static final String SYSTEM_USER_HARLEY_READER = "getResource";
	    
	    /**
	     *
	     * @param resourceResolverFactory
	     *            resource resolver Factory object from caller.
	     * @param user
	     *            user with whose permission the resource can be resolved.
	     * @return Resource resolver.
	     * @throws LoginException
	     *             if login of the user failed.
	     */
	    private static ResourceResolver getResourceResolver(final ResourceResolverFactory resourceResolverFactory,
	            final String user) throws LoginException {
	        final Map<String, Object> serviceAuthMap = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE,
	                (Object) user);
	        return resourceResolverFactory.getServiceResourceResolver(serviceAuthMap);
	    }
	 
	    /**
	     * Gives the Resource Resolver which has read access to system user.
	     *
	     * @param resourceResolverFactory
	     *            resourceResolverFactory reference passed from calling method.
	     * @return Resource Resolver which has read access to system user.
	     * @throws LoginException
	     *             if login of the user failed.
	     */
	    public static ResourceResolver getReadResourceResolver(final ResourceResolverFactory resourceResolverFactory)
	            throws LoginException {
	        return getResourceResolver(resourceResolverFactory, SYSTEM_USER_HARLEY_READER);
	    }
	 
	    /**
	     * It's used to close the resource resolver after we used the resolve resolver.
	     *
	     * @param resourceResolver
	     *            resource resolver was opened which has read access to system user.
	     */
	    public static void closeResourceResolver(final ResourceResolver resourceResolver) {
	        if (resourceResolver != null && resourceResolver.isLive()) {
	            resourceResolver.close();
	        }
	    }
 
}