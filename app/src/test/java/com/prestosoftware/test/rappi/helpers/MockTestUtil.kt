package com.prestosoftware.test.rappi.helpers

import com.prestosoftware.test.rappi.models.Keyword
import com.prestosoftware.test.rappi.models.Video
import com.prestosoftware.test.rappi.models.entity.Movie

class MockTestUtil {
	companion object {

		fun mockMovie() = Movie(1, emptyList(), emptyList(), "",
			false, "", "", ArrayList(), 123,
			"", "", "", "", 0f,
			0, false, 0f)

		fun mockKeywordList(): List<Keyword> {
			val keywords = ArrayList<Keyword>()
			keywords.add(Keyword(100, "Key0"))
			keywords.add(Keyword(101, "key1"))
			keywords.add(Keyword(102, "key2"))
			return keywords
		}

		fun mockVideoList(): List<Video> {
			val videos = ArrayList<Video>()
			videos.add(Video("123", "video0", "", "", 0, ""))
			videos.add(Video("123", "video0", "", "", 0, ""))
			return videos
		}

	}
}
