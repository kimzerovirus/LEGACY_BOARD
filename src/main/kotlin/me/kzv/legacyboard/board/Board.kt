package me.kzv.legacyboard.board

import jakarta.persistence.*
import me.kzv.legacyboard.infra.common.jpa.BaseEntity
import me.kzv.legacyboard.member.Member
import me.kzv.legacyboard.reply.Reply
import me.kzv.legacyboard.tag.BoardTag

@Entity
class Board(
    /** 글쓴이 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    /** 댓글 */
    @OneToMany(mappedBy = "board", cascade = [CascadeType.REMOVE])
    val replyList: Set<Reply> = mutableSetOf(),

    /** 태그 */
    @OneToMany(mappedBy = "board")
    val tags: Set<BoardTag> = mutableSetOf(),

    /** 글 제목 */
    @Column(nullable = false)
    var title: String,

    /** 글 내용 */
    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,

//    /** 이미지 */
//    @ElementCollection
//    @CollectionTable(name = "board_img", joinColumns = [JoinColumn(name = "board_id")])
//    var images: MutableList<String> = mutableListOf(),
) : BaseEntity() {
    /** 조회수 */
    var count: Int = -1

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }

//    fun updateImages(images: MutableList<String>) {
//        this.images = images
//    }

    fun updateCount() {
        this.count += 1
    }
}