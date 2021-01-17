package com.example.squadmaker.model.remotedatasource

import android.util.Log
import com.example.squadmaker.BuildConfig
import com.example.squadmaker.model.remotedatasource.apis.*
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsDataDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO
import retrofit2.HttpException
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class RemoteDataSourceImpl
@Inject
constructor(
    private val charactersApi: CharactersApi,
    private val comicsApi: ComicsApi,
    creatorsApi: CreatorsApi,
    eventsApi: EventsApi,
    seriesApi: SeriesApi,
    storiesApi: StoriesApi
) :
    RemoteDataSource {

    // region Fields

    private val TAG = RemoteDataSource::class.java.simpleName

    // endregion

    // region Implements RemoteDataSource

    override suspend fun fetchCharacters(): List<CharacterResultsDTO> {
        val md5 = getMd5Hash()
        val ts = getCurrentTimestamp()

        return try {
            val response = charactersApi.getCharacters(ts = ts, hash = md5)
            response.charactersDataDTO.charactersResultsDTOList
        } catch (e: HttpException) {
            Log.e(TAG, e.message())
            listOf()
        }
    }

    override suspend fun fetchDetailedCharacterById(characterId: Int): CharacterResultsDTO {
        val md5 = getMd5Hash()
        val ts = getCurrentTimestamp()

        val response =
            charactersApi.getCharacterById(characterId.toString(), ts = ts, hash = md5)
        return response.charactersDataDTO.charactersResultsDTOList[0]
    }

    override suspend fun fetchComicsForCharacterId(characterId: Int): ComicsResponseDTO {
        val md5 = getMd5Hash()
        val ts = getCurrentTimestamp()

        return try {
            charactersApi.getComicsForCharacterId(
                characterId = characterId.toString(),
                ts = ts,
                hash = md5
            )
        } catch (e: HttpException) {
            Log.e(TAG, e.message())
            ComicsResponseDTO(
                code = 0,
                status = "",
                comicsDataDTO = ComicsDataDTO(limit = 0, total = 0, comicsResultsDTOList = listOf())
            )
        }
    }

    // endregion

    // region Private Functions

    private fun getMd5Hash(): String {
        val md5input = getMd5Input()
        return md5input.md5()
    }

    private fun getMd5Input(): String {
        return (getCurrentTimestamp())
            .plus(BuildConfig.PRIVATE_API_KEY)
            .plus(BuildConfig.PUBLIC_API_KEY)
    }

    private fun getCurrentTimestamp(): String {
        return (System.currentTimeMillis() / 1000L).toString()
    }

    // endregion

    // region Extension Functions

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    // endregion
}