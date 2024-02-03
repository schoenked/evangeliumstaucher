# ChaptersApi

All URIs are relative to *https://api.scripture.api.bible*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAudioChapter**](ChaptersApi.md#getAudioChapter) | **GET** /v1/audio-bibles/{audioBibleId}/chapters/{chapterId} | 
[**getAudioChapters**](ChaptersApi.md#getAudioChapters) | **GET** /v1/audio-bibles/{audioBibleId}/books/{bookId}/chapters | 
[**getChapter**](ChaptersApi.md#getChapter) | **GET** /v1/bibles/{bibleId}/chapters/{chapterId} | 
[**getChapters**](ChaptersApi.md#getChapters) | **GET** /v1/bibles/{bibleId}/books/{bookId}/chapters | 


<a name="getAudioChapter"></a>
# **getAudioChapter**
> InlineResponse20013 getAudioChapter(audioBibleId, chapterId)



Gets a single &#x60;Chapter&#x60; object for a given &#x60;audioBibleId&#x60; and &#x60;chapterId&#x60;. This AudioChapter object also includes an &#x60;resourceUrl&#x60; property with a HTTP URL to the mp3 audio resource for the chapter.  The &#x60;resourceUrl&#x60; is unique per request and expires in XX minutes.  The &#x60;expiresAt&#x60; property provides the Unix time value of &#x60;resourceUrl&#x60; expiration. 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.ChaptersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    ChaptersApi apiInstance = new ChaptersApi(defaultClient);
    String audioBibleId = "audioBibleId_example"; // String | Id of Bible whose Chapter to fetch
    String chapterId = "chapterId_example"; // String | Id of the Chapter to fetch
    try {
      InlineResponse20013 result = apiInstance.getAudioChapter(audioBibleId, chapterId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChaptersApi#getAudioChapter");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **audioBibleId** | **String**| Id of Bible whose Chapter to fetch |
 **chapterId** | **String**| Id of the Chapter to fetch |

### Return type

[**InlineResponse20013**](InlineResponse20013.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | Invalid ID supplied |  -  |
**401** | Unauthorized for API access.  Missing or Invalid API Token provided. |  -  |
**403** | Not authorized to retrieve Chapters for this Bible |  -  |
**404** | Chapter not found |  -  |

<a name="getAudioChapters"></a>
# **getAudioChapters**
> InlineResponse2004 getAudioChapters(audioBibleId, bookId)



Gets an array of &#x60;Chapter&#x60; objects for a given &#x60;audioBibleId&#x60; and &#x60;bookId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.ChaptersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    ChaptersApi apiInstance = new ChaptersApi(defaultClient);
    String audioBibleId = "audioBibleId_example"; // String | Id of Bible whose Chapters to fetch
    String bookId = "bookId_example"; // String | Id of the Book whose Chapters to fetch
    try {
      InlineResponse2004 result = apiInstance.getAudioChapters(audioBibleId, bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChaptersApi#getAudioChapters");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **audioBibleId** | **String**| Id of Bible whose Chapters to fetch |
 **bookId** | **String**| Id of the Book whose Chapters to fetch |

### Return type

[**InlineResponse2004**](InlineResponse2004.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | Invalid ID supplied |  -  |
**401** | Unauthorized for API access.  Missing or Invalid API Token provided. |  -  |
**403** | Not authorized to retrieve Chapters for this Bible |  -  |
**404** | Book not found |  -  |

<a name="getChapter"></a>
# **getChapter**
> InlineResponse2005 getChapter(bibleId, chapterId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels)



Gets a single &#x60;Chapter&#x60; object for a given &#x60;bibleId&#x60; and &#x60;chapterId&#x60;. This Chapter object also includes an &#x60;content&#x60; property with all verses for the Chapter. 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.ChaptersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    ChaptersApi apiInstance = new ChaptersApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible whose Chapter to fetch
    String chapterId = "chapterId_example"; // String | Id of the Chapter to fetch
    String contentType = "html"; // String | Content type to be returned in the content property.  Supported values are `html` (default), `json` (beta), and `text` (beta)
    Boolean includeNotes = false; // Boolean | Include footnotes in content
    Boolean includeTitles = true; // Boolean | Include section titles in content
    Boolean includeChapterNumbers = false; // Boolean | Include chapter numbers in content
    Boolean includeVerseNumbers = true; // Boolean | Include verse numbers in content.
    Boolean includeVerseSpans = false; // Boolean | Include spans that wrap verse numbers and verse text for bible content.
    String parallels = "parallels_example"; // String | Comma delimited list of bibleIds to include
    try {
      InlineResponse2005 result = apiInstance.getChapter(bibleId, chapterId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChaptersApi#getChapter");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bibleId** | **String**| Id of Bible whose Chapter to fetch |
 **chapterId** | **String**| Id of the Chapter to fetch |
 **contentType** | **String**| Content type to be returned in the content property.  Supported values are &#x60;html&#x60; (default), &#x60;json&#x60; (beta), and &#x60;text&#x60; (beta) | [optional] [default to html] [enum: html, json, text]
 **includeNotes** | **Boolean**| Include footnotes in content | [optional] [default to false]
 **includeTitles** | **Boolean**| Include section titles in content | [optional] [default to true]
 **includeChapterNumbers** | **Boolean**| Include chapter numbers in content | [optional] [default to false]
 **includeVerseNumbers** | **Boolean**| Include verse numbers in content. | [optional] [default to true]
 **includeVerseSpans** | **Boolean**| Include spans that wrap verse numbers and verse text for bible content. | [optional] [default to false]
 **parallels** | **String**| Comma delimited list of bibleIds to include | [optional]

### Return type

[**InlineResponse2005**](InlineResponse2005.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | Invalid ID supplied |  -  |
**401** | Unauthorized for API access.  Missing or Invalid API Token provided. |  -  |
**403** | Not authorized to retrieve Chapters for this Bible |  -  |
**404** | Chapter not found |  -  |

<a name="getChapters"></a>
# **getChapters**
> InlineResponse2004 getChapters(bibleId, bookId)



Gets an array of &#x60;Chapter&#x60; objects for a given &#x60;bibleId&#x60; and &#x60;bookId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.ChaptersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    ChaptersApi apiInstance = new ChaptersApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible whose Chapters to fetch
    String bookId = "bookId_example"; // String | Id of the Book whose Chapters to fetch
    try {
      InlineResponse2004 result = apiInstance.getChapters(bibleId, bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChaptersApi#getChapters");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bibleId** | **String**| Id of Bible whose Chapters to fetch |
 **bookId** | **String**| Id of the Book whose Chapters to fetch |

### Return type

[**InlineResponse2004**](InlineResponse2004.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | Invalid ID supplied |  -  |
**401** | Unauthorized for API access.  Missing or Invalid API Token provided. |  -  |
**403** | Not authorized to retrieve Chapters for this Bible |  -  |
**404** | Book not found |  -  |

