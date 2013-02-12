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
    assertEquals("true", env.get("isSSL"));
    assertEquals("https://www.example.com/member/", env.get("secureHome"));
    assertEquals("http://www.example.com/member/", env.get("home"));
    assertEquals("https://www.example.com/", env.get("secureGuestSiteHome"));
    assertEquals("https://www.example.com/", env.get("secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", env.get("secureMemberSiteHome"));
    assertEquals("https://www.example.com/member/", env.get("secureHome"));
    assertEquals("http://www.example.com/member/", env.get("memberSiteHome"));
    assertEquals("http://www.example.com/", env.get("guestSiteHome"));
    assertEquals("http://www.example.com/", env.get("falconSiteHome"));
  }

  @Test
  public void testSecureNotLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = true;
    boolean loggedInUser = false;
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", env.get("isSSL"));
    assertEquals("https://www.example.com/", env.get("secureHome"));
    assertEquals("http://www.example.com/", env.get("home"));
    assertEquals("https://www.example.com/", env.get("secureGuestSiteHome"));
    assertEquals("https://www.example.com/", env.get("secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", env.get("secureMemberSiteHome"));
    assertEquals("https://www.example.com/", env.get("secureHome"));
    assertEquals("http://www.example.com/member/", env.get("memberSiteHome"));
    assertEquals("http://www.example.com/", env.get("guestSiteHome"));
    assertEquals("http://www.example.com/", env.get("falconSiteHome"));
  }

  @Test
  public void testNotSecureLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = false;
    boolean loggedInUser = true;
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", env.get("isSSL"));
    assertEquals("https://www.example.com/member/", env.get("secureHome"));
    assertEquals("http://www.example.com/member/", env.get("home"));
    assertEquals("https://www.example.com/", env.get("secureGuestSiteHome"));
    assertEquals("https://www.example.com/", env.get("secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", env.get("secureMemberSiteHome"));
    assertEquals("https://www.example.com/member/", env.get("secureHome"));
    assertEquals("http://www.example.com/member/", env.get("memberSiteHome"));
    assertEquals("http://www.example.com/", env.get("guestSiteHome"));
    assertEquals("http://www.example.com/", env.get("falconSiteHome"));
  }

  @Test
  public void testNotSecureNotLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter("www.example.com/");

    boolean isSecure = true;
    boolean loggedInUser = true;
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    assertEquals("true", env.get("isSSL"));
    assertEquals("https://www.example.com/member/", env.get("secureHome"));
    assertEquals("http://www.example.com/member/", env.get("home"));
    assertEquals("https://www.example.com/", env.get("secureGuestSiteHome"));
    assertEquals("https://www.example.com/", env.get("secureFalconSiteHome"));
    assertEquals("https://www.example.com/member/", env.get("secureMemberSiteHome"));
    assertEquals("https://www.example.com/", env.get("secureHome"));
    assertEquals("http://www.example.com/member/", env.get("memberSiteHome"));
    assertEquals("http://www.example.com/", env.get("guestSiteHome"));
    assertEquals("http://www.example.com/", env.get("falconSiteHome")); }


}
