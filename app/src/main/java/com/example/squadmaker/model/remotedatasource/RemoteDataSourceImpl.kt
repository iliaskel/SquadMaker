package com.example.squadmaker.model.remotedatasource

import android.util.Log
import com.example.squadmaker.model.remotedatasource.retrofit.api.MarvelApiService
import com.example.squadmaker.model.remotedatasource.retrofit.characterresponse.CharacterDTO
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.ComicsResponseDTO
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.DataDTO
import com.example.squadmaker.utils.Constants
import retrofit2.HttpException
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class RemoteDataSourceImpl
@Inject
constructor(private val marvelApiService: MarvelApiService) : RemoteDataSource {

    // region Fields

    private val TAG = RemoteDataSource::class.java.simpleName

    // endregion

    // region Implements RemoteDataSource

    override suspend fun fetchCharacters(): List<CharacterDTO> {
        val md5 = getMd5Hash()
        val ts = getCurrentTimestamp()

        return try {
            val response = marvelApiService.getCharacters(ts = ts, hash = md5)
            response.dataDTO.results
        } catch (e: HttpException) {
            Log.e(TAG, e.message())
            listOf()
        }
    }

    override suspend fun fetchDetailedCharacterById(characterId: Int): CharacterDTO {
        val md5 = getMd5Hash()
        val ts = getCurrentTimestamp()

        val response =
            marvelApiService.getCharacterById(characterId.toString(), ts = ts, hash = md5)
        return response.dataDTO.results[0]
    }

    override suspend fun getComicsForCharacterId(characterId: Int): ComicsResponseDTO {
        val md5 = getMd5Hash()
        val ts = getCurrentTimestamp()

        return try {
            marvelApiService.getComicsByCharacterId(
                characterId = characterId.toString(),
                ts = ts,
                hash = md5
            )
        } catch (e: HttpException) {
            Log.e(TAG, e.message())
            ComicsResponseDTO(
                code = "",
                status = "",
                dataDTO = DataDTO(limit = "", total = "", results = listOf())
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
            .plus(Constants.PRIVATE_API_KEY)
            .plus(Constants.PUBLIC_API_KEY)
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