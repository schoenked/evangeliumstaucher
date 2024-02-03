# BooksApi

All URIs are relative to *https://api.scripture.api.bible*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAudioBook**](BooksApi.md#getAudioBook) | **GET** /v1/audio-bibles/{audioBibleId}/books/{bookId} | 
[**getAudioBooks**](BooksApi.md#getAudioBooks) | **GET** /v1/audio-bibles/{audioBibleId}/books | 
[**getBook**](BooksApi.md#getBook) | **GET** /v1/bibles/{bibleId}/books/{bookId} | 
[**getBooks**](BooksApi.md#getBooks) | **GET** /v1/bibles/{bibleId}/books | 


<a name="getAudioBook"></a>
# **getAudioBook**
> InlineResponse2003 getAudioBook(audioBibleId, bookId, includeChapters)



Gets a single &#x60;Book&#x60; object for a given &#x60;audioBibleId&#x60; and &#x60;bookId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    BooksApi apiInstance = new BooksApi(defaultClient);
    String audioBibleId = "audioBibleId_example"; // String | Id of audio Bible whose Book to fetch
    String bookId = "bookId_example"; // String | Id of the Book to fetch
    Boolean includeChapters = true; // Boolean | Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false. 
    try {
      InlineResponse2003 result = apiInstance.getAudioBook(audioBibleId, bookId, includeChapters);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#getAudioBook");
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
 **audioBibleId** | **String**| Id of audio Bible whose Book to fetch |
 **bookId** | **String**| Id of the Book to fetch |
 **includeChapters** | **Boolean**| Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  | [optional]

### Return type

[**InlineResponse2003**](InlineResponse2003.md)

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
**403** | Not authorized to retrieve Books for this Bible |  -  |
**404** | Book not found |  -  |

<a name="getAudioBooks"></a>
# **getAudioBooks**
> InlineResponse2002 getAudioBooks(audioBibleId, includeChapters, includeChaptersAndSections)



Gets an array of &#x60;Book&#x60; objects for a given &#x60;audioBibleId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    BooksApi apiInstance = new BooksApi(defaultClient);
    String audioBibleId = "audioBibleId_example"; // String | Id of audio Bible whose Book to fetch
    Boolean includeChapters = true; // Boolean | Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false. 
    Boolean includeChaptersAndSections = true; // Boolean | Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false. 
    try {
      InlineResponse2002 result = apiInstance.getAudioBooks(audioBibleId, includeChapters, includeChaptersAndSections);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#getAudioBooks");
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
 **audioBibleId** | **String**| Id of audio Bible whose Book to fetch |
 **includeChapters** | **Boolean**| Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  | [optional]
 **includeChaptersAndSections** | **Boolean**| Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  | [optional]

### Return type

[**InlineResponse2002**](InlineResponse2002.md)

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
**403** | Not authorized to retrieve Books for this Bible |  -  |

<a name="getBook"></a>
# **getBook**
> InlineResponse2003 getBook(bibleId, bookId, includeChapters)



Gets a single &#x60;Book&#x60; object for a given &#x60;bibleId&#x60; and &#x60;bookId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    BooksApi apiInstance = new BooksApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible whose Book to fetch
    String bookId = "bookId_example"; // String | Id of the Book to fetch
    Boolean includeChapters = true; // Boolean | Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false. 
    try {
      InlineResponse2003 result = apiInstance.getBook(bibleId, bookId, includeChapters);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#getBook");
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
 **bibleId** | **String**| Id of Bible whose Book to fetch |
 **bookId** | **String**| Id of the Book to fetch |
 **includeChapters** | **Boolean**| Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  | [optional]

### Return type

[**InlineResponse2003**](InlineResponse2003.md)

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
**403** | Not authorized to retrieve Books for this Bible |  -  |
**404** | Book not found |  -  |

<a name="getBooks"></a>
# **getBooks**
> InlineResponse2002 getBooks(bibleId, includeChapters, includeChaptersAndSections)



Gets an array of &#x60;Book&#x60; objects for a given &#x60;bibleId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.BooksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    BooksApi apiInstance = new BooksApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible whose Book to fetch
    Boolean includeChapters = true; // Boolean | Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false. 
    Boolean includeChaptersAndSections = true; // Boolean | Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false. 
    try {
      InlineResponse2002 result = apiInstance.getBooks(bibleId, includeChapters, includeChaptersAndSections);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BooksApi#getBooks");
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
 **bibleId** | **String**| Id of Bible whose Book to fetch |
 **includeChapters** | **Boolean**| Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  | [optional]
 **includeChaptersAndSections** | **Boolean**| Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  | [optional]

### Return type

[**InlineResponse2002**](InlineResponse2002.md)

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
**403** | Not authorized to retrieve Books for this Bible |  -  |

