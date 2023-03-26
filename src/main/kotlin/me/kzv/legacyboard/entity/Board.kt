package me.kzv.legacyboard.entity

import jakarta.persistence.*

@Entity
class Board(
    /** 글쓴이 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    /** 댓글 */
    @OneToMany(mappedBy = "board", cascade = [CascadeType.REMOVE])
    val replyList: MutableList<Reply> = mutableListOf(),

    /** 글 제목 */
    @Column(nullable = false)
    var title: String,

    /** 글 내용 */
    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,

    /** 이미지 */
    @ElementCollection
    @CollectionTable(name = "product_img", joinColumns = [JoinColumn(name = "product_id")])
    var images: MutableList<String> = mutableListOf(),
) : BaseEntity() {
    var count: Int = 0

    fun update(title: String, content: String, images: MutableList<String>) {
        this.title = title
        this.content = content
        this.images = images
    }

    fun updateCount() {
        this.count += 1
    }
}