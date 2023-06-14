package me.kzv.legacyboard.tag

import me.kzv.legacyboard.board.Board
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TagService(
    private val tagRepository: TagRepository
) {
    @Transactional(readOnly = true)
    fun search(name: String): List<Tag> {
        return tagRepository.findByNameLike("%$name%")
    }

    @Transactional
    fun searchAndCreate(tags: List<Tag>): List<BoardTag> {
        return tags.map {
            if(it.id == null) BoardTag(tagRepository.save(it))
            else BoardTag(it)
        }
    }
}
