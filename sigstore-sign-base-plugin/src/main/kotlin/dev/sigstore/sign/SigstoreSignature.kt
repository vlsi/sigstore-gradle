/*
 * Copyright 2022 The Sigstore Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package dev.sigstore.sign

import org.gradle.api.Buildable
import org.gradle.api.Named
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import javax.inject.Inject

abstract class SigstoreSignature @Inject constructor(private val name: String) : Named {
    companion object {
        const val EXTENSION = "sigstore"
    }

    // Gradle 6.8.3: Cannot have abstract method SigstoreSignature.getName
    @Internal
    override fun getName(): String = name

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val file: RegularFileProperty

    @get:Internal
    abstract val builtBy: Property<Buildable>

    @get:OutputFile
    abstract val outputSignature: RegularFileProperty

    @get:Internal
    abstract val signatureDirectory: DirectoryProperty

    init {
        outputSignature.convention(
            signatureDirectory.map { it.file(file.get().asFile.name + ".$EXTENSION") }
        )
    }
}
