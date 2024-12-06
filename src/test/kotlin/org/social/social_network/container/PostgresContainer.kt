package org.social.social_network.container

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

object PostgresContainer {

    val instance by lazy { createContainer() }

    private fun createContainer(): PostgreSQLContainer<Nothing> {
        val container = PostgreSQLContainer<Nothing>(
            DockerImageName.parse("postgres:14.6-alpine")
                .asCompatibleSubstituteFor("postgres")
        )
        container.start()
        System.setProperty("spring.datasource.url", container.jdbcUrl)
        System.setProperty("spring.datasource.username", container.username)
        System.setProperty("spring.datasource.password", container.password)
        return container
    }
}