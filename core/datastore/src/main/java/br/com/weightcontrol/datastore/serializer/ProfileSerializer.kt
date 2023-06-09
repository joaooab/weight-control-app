package br.com.weightcontrol.datastore.serializer

import androidx.datastore.core.Serializer
import br.com.weightcontrol.datastore.ProfilePreference
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class ProfilePreferencesSerializer : Serializer<ProfilePreference> {
    override val defaultValue: ProfilePreference = ProfilePreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProfilePreference =
        try {
            ProfilePreference.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            defaultValue
        }

    override suspend fun writeTo(t: ProfilePreference, output: OutputStream) {
        t.writeTo(output)
    }
}