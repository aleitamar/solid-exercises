package com.theladders.solid.lsp;

import static org.junit.Assert.*;

import org.junit.*;

public class EnvironmentTest {
  @Test
  public void testSecureLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = true;
    boolean loggedInUser = true;
    Environment environment = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", getValueForKey(environment,"isSSL"));
    assertEquals("https://www.example.com/member/", getValueForKey(environment,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(environment,"home"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureGuestSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(environment,"secureMemberSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(environment,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(environment,"memberSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"guestSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"falconSiteHome"));
  }

  @Test
  public void testSecureNotLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = true;
    boolean loggedInUser = false;
    Environment environment = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", getValueForKey(environment,"isSSL"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"home"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureGuestSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(environment,"secureMemberSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(environment,"memberSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"guestSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"falconSiteHome"));
  }

  @Test
  public void testNotSecureLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = false;
    boolean loggedInUser = true;
    Environment environment = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("false", getValueForKey(environment,"isSSL"));
    assertEquals("https://www.example.com/member/", getValueForKey(environment,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(environment,"home"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureGuestSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(environment,"secureMemberSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(environment,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(environment,"memberSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"guestSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"falconSiteHome"));
  }

  @Test
  public void testNotSecureNotLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = false;
    boolean loggedInUser = false;
    Environment environment = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("false", getValueForKey(environment,"isSSL"));
    assertEquals("https://www.example.com/", getValueForKey(environment, "secureHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"home"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureGuestSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(environment,"secureMemberSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(environment,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(environment,"memberSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"guestSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(environment,"falconSiteHome"));
  }

  private Object getValueForKey(Environment environment, String key)
  {
    return environment.getValue(key);
  }

}
