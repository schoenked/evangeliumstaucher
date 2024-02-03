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
import de.evangeliumstaucher.model.BibleSummaryCountries;
import de.evangeliumstaucher.model.Language;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * AudioBible
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-02-01T23:51:10.720725+01:00[Europe/Berlin]")
public class AudioBible {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;

  public static final String SERIALIZED_NAME_DBL_ID = "dblId";
  @SerializedName(SERIALIZED_NAME_DBL_ID)
  private String dblId;

  public static final String SERIALIZED_NAME_ABBREVIATION = "abbreviation";
  @SerializedName(SERIALIZED_NAME_ABBREVIATION)
  private String abbreviation;

  public static final String SERIALIZED_NAME_ABBREVIATION_LOCAL = "abbreviationLocal";
  @SerializedName(SERIALIZED_NAME_ABBREVIATION_LOCAL)
  private String abbreviationLocal;

  public static final String SERIALIZED_NAME_COPYRIGHT = "copyright";
  @SerializedName(SERIALIZED_NAME_COPYRIGHT)
  private String copyright;

  public static final String SERIALIZED_NAME_LANGUAGE = "language";
  @SerializedName(SERIALIZED_NAME_LANGUAGE)
  private Language language;

  public static final String SERIALIZED_NAME_COUNTRIES = "countries";
  @SerializedName(SERIALIZED_NAME_COUNTRIES)
  private List<BibleSummaryCountries> countries = new ArrayList<>();

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

  public static final String SERIALIZED_NAME_INFO = "info";
  @SerializedName(SERIALIZED_NAME_INFO)
  private String info;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type;

  public static final String SERIALIZED_NAME_UPDATED_AT = "updatedAt";
  @SerializedName(SERIALIZED_NAME_UPDATED_AT)
  private OffsetDateTime updatedAt;

  public static final String SERIALIZED_NAME_RELATED_DBL = "relatedDbl";
  @SerializedName(SERIALIZED_NAME_RELATED_DBL)
  private String relatedDbl;

  public AudioBible() { 
  }

  public AudioBible id(String id) {
    
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


  public AudioBible dblId(String dblId) {
    
    this.dblId = dblId;
    return this;
  }

   /**
   * Get dblId
   * @return dblId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getDblId() {
    return dblId;
  }


  public void setDblId(String dblId) {
    this.dblId = dblId;
  }


  public AudioBible abbreviation(String abbreviation) {
    
    this.abbreviation = abbreviation;
    return this;
  }

   /**
   * Get abbreviation
   * @return abbreviation
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getAbbreviation() {
    return abbreviation;
  }


  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }


  public AudioBible abbreviationLocal(String abbreviationLocal) {
    
    this.abbreviationLocal = abbreviationLocal;
    return this;
  }

   /**
   * Get abbreviationLocal
   * @return abbreviationLocal
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getAbbreviationLocal() {
    return abbreviationLocal;
  }


  public void setAbbreviationLocal(String abbreviationLocal) {
    this.abbreviationLocal = abbreviationLocal;
  }


  public AudioBible copyright(String copyright) {
    
    this.copyright = copyright;
    return this;
  }

   /**
   * Get copyright
   * @return copyright
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getCopyright() {
    return copyright;
  }


  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }


  public AudioBible language(Language language) {
    
    this.language = language;
    return this;
  }

   /**
   * Get language
   * @return language
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Language getLanguage() {
    return language;
  }


  public void setLanguage(Language language) {
    this.language = language;
  }


  public AudioBible countries(List<BibleSummaryCountries> countries) {
    
    this.countries = countries;
    return this;
  }

  public AudioBible addCountriesItem(BibleSummaryCountries countriesItem) {
    this.countries.add(countriesItem);
    return this;
  }

   /**
   * Get countries
   * @return countries
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public List<BibleSummaryCountries> getCountries() {
    return countries;
  }


  public void setCountries(List<BibleSummaryCountries> countries) {
    this.countries = countries;
  }


  public AudioBible name(String name) {
    
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


  public AudioBible nameLocal(String nameLocal) {
    
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


  public AudioBible description(String description) {
    
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


  public AudioBible descriptionLocal(String descriptionLocal) {
    
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


  public AudioBible info(String info) {
    
    this.info = info;
    return this;
  }

   /**
   * Get info
   * @return info
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getInfo() {
    return info;
  }


  public void setInfo(String info) {
    this.info = info;
  }


  public AudioBible type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public AudioBible updatedAt(OffsetDateTime updatedAt) {
    
    this.updatedAt = updatedAt;
    return this;
  }

   /**
   * Get updatedAt
   * @return updatedAt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }


  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }


  public AudioBible relatedDbl(String relatedDbl) {
    
    this.relatedDbl = relatedDbl;
    return this;
  }

   /**
   * Get relatedDbl
   * @return relatedDbl
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getRelatedDbl() {
    return relatedDbl;
  }


  public void setRelatedDbl(String relatedDbl) {
    this.relatedDbl = relatedDbl;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AudioBible audioBible = (AudioBible) o;
    return Objects.equals(this.id, audioBible.id) &&
        Objects.equals(this.dblId, audioBible.dblId) &&
        Objects.equals(this.abbreviation, audioBible.abbreviation) &&
        Objects.equals(this.abbreviationLocal, audioBible.abbreviationLocal) &&
        Objects.equals(this.copyright, audioBible.copyright) &&
        Objects.equals(this.language, audioBible.language) &&
        Objects.equals(this.countries, audioBible.countries) &&
        Objects.equals(this.name, audioBible.name) &&
        Objects.equals(this.nameLocal, audioBible.nameLocal) &&
        Objects.equals(this.description, audioBible.description) &&
        Objects.equals(this.descriptionLocal, audioBible.descriptionLocal) &&
        Objects.equals(this.info, audioBible.info) &&
        Objects.equals(this.type, audioBible.type) &&
        Objects.equals(this.updatedAt, audioBible.updatedAt) &&
        Objects.equals(this.relatedDbl, audioBible.relatedDbl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dblId, abbreviation, abbreviationLocal, copyright, language, countries, name, nameLocal, description, descriptionLocal, info, type, updatedAt, relatedDbl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AudioBible {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dblId: ").append(toIndentedString(dblId)).append("\n");
    sb.append("    abbreviation: ").append(toIndentedString(abbreviation)).append("\n");
    sb.append("    abbreviationLocal: ").append(toIndentedString(abbreviationLocal)).append("\n");
    sb.append("    copyright: ").append(toIndentedString(copyright)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    countries: ").append(toIndentedString(countries)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    nameLocal: ").append(toIndentedString(nameLocal)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    descriptionLocal: ").append(toIndentedString(descriptionLocal)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    relatedDbl: ").append(toIndentedString(relatedDbl)).append("\n");
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

