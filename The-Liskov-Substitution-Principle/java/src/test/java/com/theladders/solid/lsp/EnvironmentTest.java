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
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", getValueForKey(env,"isSSL"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(env,"home"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureGuestSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureMemberSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(env,"memberSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"guestSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"falconSiteHome"));
  }

  @Test
  public void testSecureNotLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = true;
    boolean loggedInUser = false;
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", getValueForKey(env,"isSSL"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"home"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureGuestSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureMemberSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(env,"memberSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"guestSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"falconSiteHome"));
  }

  @Test
  public void testNotSecureLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = false;
    boolean loggedInUser = true;
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", getValueForKey(env,"isSSL"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(env,"home"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureGuestSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureMemberSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(env,"memberSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"guestSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"falconSiteHome"));
  }

  @Test
  public void testNotSecureNotLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = true;
    boolean loggedInUser = true;
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", getValueForKey(env,"isSSL"));
    assertEquals("https://www.example.com/member/", getValueForKey(env, "secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(env,"home"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureGuestSiteHome"));
    assertEquals("https://www.example.com/", getValueForKey(env,"secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureMemberSiteHome"));
    assertEquals("https://www.example.com/member/", getValueForKey(env,"secureHome"));
    assertEquals("http://www.example.com/member/", getValueForKey(env,"memberSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"guestSiteHome"));
    assertEquals("http://www.example.com/", getValueForKey(env,"falconSiteHome"));
  }

  private Object getValueForKey(Environment environment, String key)
  {
    return environment.get(key);
  }

}
