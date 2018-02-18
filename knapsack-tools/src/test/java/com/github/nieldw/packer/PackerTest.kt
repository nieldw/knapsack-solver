package com.github.nieldw.packer

import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertFailsWith
import kotlin.test.expect

internal class PackerTest {
    @Test
    fun `pack with an empty file should return an empty string`() {
        val resource = PackerTest::class.java.getResource("/empty.txt")
        val file = Paths.get(resource.toURI()).toFile()

        expect("", {
            Packer.pack(file.absolutePath)
        })
    }

    @Test
    fun `pack should throw an APIException if the file is not found`() {
        assertFailsWith<APIException> { Packer.pack("does not exist") }
    }

    @Test
    fun `sample problems`() {
        val sampleProblemsResource = PackerTest::class.java.getResource("/sampleKnapsackProblems.txt")
        val sampleProblemsFile = Paths.get(sampleProblemsResource.toURI()).toFile()
        val sampleSolutionsResource = PackerTest::class.java.getResource("/sampleKnapsackSolutions.txt")
        val sampleSolutionsPath = Paths.get(sampleSolutionsResource.toURI())

        expect(String(Files.readAllBytes(sampleSolutionsPath)), {
            Packer.pack(sampleProblemsFile.absolutePath)
        })
    }
}
