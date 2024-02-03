/*
 * API.Bible
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.6.3
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package de.evangeliumstaucher.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * AudioBibleSummary
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-02-01T23:51:10.720725+01:00[Europe/Berlin]")
public class AudioBibleSummary {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_NAME_LOCAL = "nameLocal";
  @SerializedName(SERIALIZED_NAME_NAME_LOCAL)
  private String nameLocal;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_DESCRIPTION_LOCAL = "descriptionLocal";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION_LOCAL)
  private String descriptionLocal;

  public AudioBibleSummary() { 
  }

  public AudioBibleSummary id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public AudioBibleSummary name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public AudioBibleSummary nameLocal(String nameLocal) {
    
    this.nameLocal = nameLocal;
    return this;
  }

   /**
   * Get nameLocal
   * @return nameLocal
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getNameLocal() {
    return nameLocal;
  }


  public void setNameLocal(String nameLocal) {
    this.nameLocal = nameLocal;
  }


  public AudioBibleSummary description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public AudioBibleSummary descriptionLocal(String descriptionLocal) {
    
    this.descriptionLocal = descriptionLocal;
    return this;
  }

   /**
   * Get descriptionLocal
   * @return descriptionLocal
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getDescriptionLocal() {
    return descriptionLocal;
  }


  public void setDescriptionLocal(String descriptionLocal) {
    this.descriptionLocal = descriptionLocal;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AudioBibleSummary audioBibleSummary = (AudioBibleSummary) o;
    return Objects.equals(this.id, audioBibleSummary.id) &&
        Objects.equals(this.name, audioBibleSummary.name) &&
        Objects.equals(this.nameLocal, audioBibleSummary.nameLocal) &&
        Objects.equals(this.description, audioBibleSummary.description) &&
        Objects.equals(this.descriptionLocal, audioBibleSummary.descriptionLocal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, nameLocal, description, descriptionLocal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AudioBibleSummary {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    nameLocal: ").append(toIndentedString(nameLocal)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    descriptionLocal: ").append(toIndentedString(descriptionLocal)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

