package com.salgut.android.aicartapp.util


class Constants {
    companion object {
        const val BASE_URL = " https://api.artic.edu/api/v1/"
        const val FIELD_TERMS = "fields=" +
                "iiif_url," +
                "title," +
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