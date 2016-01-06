package com.security.project.web.general;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;

public abstract class AbstractUnitTest {
    private List<Object> verifyList = new ArrayList<Object>();

    @Before
    public void before() throws Exception {
        // place holder for future setup before each test is run
    }

    @After
    public void after() throws Exception {
	// (Hopefully) Useful tip: if you've gotten here via a "no more interactions" warning out of a junit 
    // test then a mockito object received a call you didn't tell it that it would be receiving.  for example
    // the code snippet:
    	
//		try {
//			ModelAndView mav = psc.view(request, session, status);
//			Assert.assertEquals("error500", mav.getViewName());
//		} finally {
//			Mockito.verify(identityService, Mockito.times(1)).guestView(westJetId);
//         ....
//
    // would throw these errors if the line starting with 'Mockito.verify' were commented out because this verify
    // call asserts that the identity service was called one time.  The stack trace 
    // on the error will indicate which reference to a mockito mock object raised the error and will be useful in
    // tracking it down.
	for (Object mock : verifyList) {
	    Mockito.verifyNoMoreInteractions(mock);
	}
    }

    /**
     * Generate your class to mock and add it for verification for no more invocations at end of test.
     * 
     * @param <T>
     *            The class type.
     * @param clazz
     *            The class to mock
     * @return the mocked class
     */
    protected <T> T mock(Class<T> clazz) {
	T mock = Mockito.mock(clazz);
	verifyList.add(mock);
	return mock;
    }

    /**
     * Retrieve the string contents of a file from the classpath. Place the file in the test/resources directory and this method will read that file
     * in for you.
     * 
     * @param path
     * @return
     * @throws IOException
     */
    protected static String readFileFromClassPathAsString(String path) throws IOException {
	StringBuilder buffer = new StringBuilder("");
	BufferedInputStream f = null;
	ClassPathResource resource = new ClassPathResource(path);
	try {
	    f = new BufferedInputStream(resource.getInputStream());
	    int c;
	    while ((c = f.read()) != -1) {
		buffer.append((char) c);
	    }
	} finally {
	    if (f != null) {
		f.close();
	    }
	}
	return buffer.toString();
    }
}
