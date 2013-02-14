package com.theladders.solid.dip;

public interface SubscriberArticleWriter
{
  public int addSuggestedArticle(SuggestedArticle suggestedArticle);
  public void updateNote(Integer id, String note);
  public void markRecomDeleted(Integer id);
}
