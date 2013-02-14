package com.theladders.solid.dip;

import java.util.List;

public interface SubscriberArticleManager
{
  public List<SuggestedArticle> getArticlesbySubscriber(Integer subscriberId);

  public int addSuggestedArticle(SuggestedArticle suggestedArticle);

  public void updateNote(Integer id, String note);

  public void markRecomDeleted(Integer id);
}