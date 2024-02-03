# SectionsApi

All URIs are relative to *https://api.scripture.api.bible*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getBookSections**](SectionsApi.md#getBookSections) | **GET** /v1/bibles/{bibleId}/books/{bookId}/sections | 
[**getChapterSections**](SectionsApi.md#getChapterSections) | **GET** /v1/bibles/{bibleId}/chapters/{chapterId}/sections | 
[**getSection**](SectionsApi.md#getSection) | **GET** /v1/bibles/{bibleId}/sections/{sectionId} | 


<a name="getBookSections"></a>
# **getBookSections**
> InlineResponse2007 getBookSections(bibleId, bookId)



Gets an array of &#x60;Section&#x60; objects for a given &#x60;bibleId&#x60; and &#x60;bookId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.SectionsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    SectionsApi apiInstance = new SectionsApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible whose Sections to fetch
    String bookId = "bookId_example"; // String | Id of the Book whose Sections to fetch
    try {
      InlineResponse2007 result = apiInstance.getBookSections(bibleId, bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SectionsApi#getBookSections");
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
 **bibleId** | **String**| Id of Bible whose Sections to fetch |
 **bookId** | **String**| Id of the Book whose Sections to fetch |

### Return type

[**InlineResponse2007**](InlineResponse2007.md)

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
**403** | Not authorized to retrieve Sections for this Bible |  -  |
**404** | Book not found |  -  |

<a name="getChapterSections"></a>
# **getChapterSections**
> InlineResponse2007 getChapterSections(bibleId, chapterId)



Gets an array of &#x60;Section&#x60; objects for a given &#x60;bibleId&#x60; and &#x60;chapterId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.SectionsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    SectionsApi apiInstance = new SectionsApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible whose Sections to fetch
    String chapterId = "chapterId_example"; // String | Id of the Chapter whose Sections to fetch
    try {
      InlineResponse2007 result = apiInstance.getChapterSections(bibleId, chapterId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SectionsApi#getChapterSections");
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
 **bibleId** | **String**| Id of Bible whose Sections to fetch |
 **chapterId** | **String**| Id of the Chapter whose Sections to fetch |

### Return type

[**InlineResponse2007**](InlineResponse2007.md)

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
**403** | Not authorized to retrieve Sections for this Bible |  -  |
**404** | Book not found |  -  |

<a name="getSection"></a>
# **getSection**
> InlineResponse2008 getSection(bibleId, sectionId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels)



Gets a single &#x60;Section&#x60; object for a given &#x60;bibleId&#x60; and &#x60;sectionId&#x60;. This Section object also includes an &#x60;content&#x60; property with all verses for the Section. 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.SectionsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    SectionsApi apiInstance = new SectionsApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible whose Section to fetch
    String sectionId = "sectionId_example"; // String | Id of the Section to fetch
    String contentType = "html"; // String | Content type to be returned in the content property.  Supported values are `html` (default), `json` (beta), and `text` (beta)
    Boolean includeNotes = false; // Boolean | Include footnotes in content
    Boolean includeTitles = true; // Boolean | Include section titles in content
    Boolean includeChapterNumbers = false; // Boolean | Include chapter numbers in content
    Boolean includeVerseNumbers = true; // Boolean | Include verse numbers in content.
    Boolean includeVerseSpans = false; // Boolean | Include spans that wrap verse numbers and verse text for bible content.
    String parallels = "parallels_example"; // String | Comma delimited list of bibleIds to include
    try {
      InlineResponse2008 result = apiInstance.getSection(bibleId, sectionId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SectionsApi#getSection");
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
 **bibleId** | **String**| Id of Bible whose Section to fetch |
 **sectionId** | **String**| Id of the Section to fetch |
 **contentType** | **String**| Content type to be returned in the content property.  Supported values are &#x60;html&#x60; (default), &#x60;json&#x60; (beta), and &#x60;text&#x60; (beta) | [optional] [default to html] [enum: html, json, text]
 **includeNotes** | **Boolean**| Include footnotes in content | [optional] [default to false]
 **includeTitles** | **Boolean**| Include section titles in content | [optional] [default to true]
 **includeChapterNumbers** | **Boolean**| Include chapter numbers in content | [optional] [default to false]
 **includeVerseNumbers** | **Boolean**| Include verse numbers in content. | [optional] [default to true]
 **includeVerseSpans** | **Boolean**| Include spans that wrap verse numbers and verse text for bible content. | [optional] [default to false]
 **parallels** | **String**| Comma delimited list of bibleIds to include | [optional]

### Return type

[**InlineResponse2008**](InlineResponse2008.md)

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
**403** | Not authorized to retrieve Sections for this Bible |  -  |
**404** | Section not found |  -  |

