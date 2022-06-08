package com.example.android.aicartapp.util

// api full string
// https://api.artic.edu/api/v1/artworks/search?fields=title,iiif_url,classification_title,id,image_id,artist_title,medium_display,provenance_text,latitude,longitude,is_public_domain,inscriptions,artist_display,iiif_url,artist_title,place_of_origin,credit_line,colorfulness&q="cats"&limit=5
// general string with fields needed, no query
// https://api.artic.edu/api/v1/artworks/search?
// to append query
// &q=cats
// compantion objects so don't need to create the Class


class Constants {
    companion object{
    const val BASE_URL = " https://api.artic.edu/api/v1/"
        const val FIELD_TERMS ="fields=" +
                "title," +
                "iiif_url," +
                "classification_title," +
                "id," +
                "style_title," +
                "image_id," +
                "artist_title," +
                "medium_display," +
                "provenance_text," +
                "latitude," +
                "longitude," +
                "is_public_domain," +
                "inscriptions," +
                "artist_display," +
                "artist_title," +
                "place_of_origin," +
                "credit_line," +
                "colorfulness"
        const val SEARCH_ART_TIME_DELAY = 600L
        const val QUERY_PAGE_SIZE = 10

    }
}