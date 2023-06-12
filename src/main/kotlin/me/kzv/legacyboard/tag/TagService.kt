package me.kzv.legacyboard.tag

import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository
){
    fun search(name: String): List<Tag> {
        return tagRepository.findByNameLike("%$name%")
    }

}