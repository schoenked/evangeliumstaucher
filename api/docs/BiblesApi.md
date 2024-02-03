# BiblesApi

All URIs are relative to *https://api.scripture.api.bible*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAudioBible**](BiblesApi.md#getAudioBible) | **GET** /v1/audio-bibles/{audioBibleId} | 
[**getAudioBibles**](BiblesApi.md#getAudioBibles) | **GET** /v1/audio-bibles | 
[**getBible**](BiblesApi.md#getBible) | **GET** /v1/bibles/{bibleId} | 
[**getBibles**](BiblesApi.md#getBibles) | **GET** /v1/bibles | 


<a name="getAudioBible"></a>
# **getAudioBible**
> InlineResponse20012 getAudioBible(audioBibleId)



Gets a single audio &#x60;Bible&#x60; for a given &#x60;audioBibleId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.BiblesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    BiblesApi apiInstance = new BiblesApi(defaultClient);
    String audioBibleId = "audioBibleId_example"; // String | Id of audio Bible to be fetched
    try {
      InlineResponse20012 result = apiInstance.getAudioBible(audioBibleId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BiblesApi#getAudioBible");
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
 **audioBibleId** | **String**| Id of audio Bible to be fetched |

### Return type

[**InlineResponse20012**](InlineResponse20012.md)

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
**403** | Not authorized to retrieve this Bible |  -  |
**404** | Bible not found |  -  |

<a name="getAudioBibles"></a>
# **getAudioBibles**
> InlineResponse200 getAudioBibles(language, abbreviation, name, ids, bibleId, includeFullDetails)



Gets an array of audio &#x60;Bible&#x60; objects authorized for current API Key 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.BiblesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    BiblesApi apiInstance = new BiblesApi(defaultClient);
    String language = "language_example"; // String | ISO 639-3 three digit language code used to filter results
    String abbreviation = "abbreviation_example"; // String | Bible abbreviation to search for
    String name = "name_example"; // String | Bible name to search for
    String ids = "ids_example"; // String | Comma separated list of Bible Ids to return
    String bibleId = "bibleId_example"; // String | bibleId of related text Bible used to filter audio bible results
    Boolean includeFullDetails = true; // Boolean | Boolean to include full Bible details (e.g. copyright and promo info)
    try {
      InlineResponse200 result = apiInstance.getAudioBibles(language, abbreviation, name, ids, bibleId, includeFullDetails);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BiblesApi#getAudioBibles");
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
 **language** | **String**| ISO 639-3 three digit language code used to filter results | [optional]
 **abbreviation** | **String**| Bible abbreviation to search for | [optional]
 **name** | **String**| Bible name to search for | [optional]
 **ids** | **String**| Comma separated list of Bible Ids to return | [optional]
 **bibleId** | **String**| bibleId of related text Bible used to filter audio bible results | [optional]
 **includeFullDetails** | **Boolean**| Boolean to include full Bible details (e.g. copyright and promo info) | [optional]

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | Not authorized to retrieve any Bibles or invalid language_code provided |  -  |
**401** | Unauthorized for API access.  Missing or Invalid API Key provided. |  -  |

<a name="getBible"></a>
# **getBible**
> InlineResponse2001 getBible(bibleId)



Gets a single &#x60;Bible&#x60; for a given &#x60;bibleId&#x60; 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.BiblesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    BiblesApi apiInstance = new BiblesApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible to be fetched
    try {
      InlineResponse2001 result = apiInstance.getBible(bibleId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BiblesApi#getBible");
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
 **bibleId** | **String**| Id of Bible to be fetched |

### Return type

[**InlineResponse2001**](InlineResponse2001.md)

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
**403** | Not authorized to retrieve this Bible |  -  |
**404** | Bible not found |  -  |

<a name="getBibles"></a>
# **getBibles**
> InlineResponse200 getBibles(language, abbreviation, name, ids, includeFullDetails)



Gets an array of &#x60;Bible&#x60; objects authorized for current API Key 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.BiblesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    BiblesApi apiInstance = new BiblesApi(defaultClient);
    String language = "language_example"; // String | ISO 639-3 three digit language code used to filter results
    String abbreviation = "abbreviation_example"; // String | Bible abbreviation to search for
    String name = "name_example"; // String | Bible name to search for
    String ids = "ids_example"; // String | Comma separated list of Bible Ids to return
    Boolean includeFullDetails = true; // Boolean | Boolean to include full Bible details (e.g. copyright and promo info)
    try {
      InlineResponse200 result = apiInstance.getBibles(language, abbreviation, name, ids, includeFullDetails);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BiblesApi#getBibles");
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
 **language** | **String**| ISO 639-3 three digit language code used to filter results | [optional]
 **abbreviation** | **String**| Bible abbreviation to search for | [optional]
 **name** | **String**| Bible name to search for | [optional]
 **ids** | **String**| Comma separated list of Bible Ids to return | [optional]
 **includeFullDetails** | **Boolean**| Boolean to include full Bible details (e.g. copyright and promo info) | [optional]

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | Not authorized to retrieve any Bibles or invalid language_code provided |  -  |
**401** | Unauthorized for API access.  Missing or Invalid API Key provided. |  -  |

