package com.theladders.solid.dip;

import java.util.List;

public interface SubscriberArticleRetriever
{
  public List<SuggestedArticle> getArticlesbySubscriber(Integer subscriberId);
}
