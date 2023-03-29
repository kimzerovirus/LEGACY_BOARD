package me.kzv.legacyboard.infra.exception

import java.lang.RuntimeException

class TisException(message: String, val slug: String? = null) : RuntimeException(message) // unckecked 예외 - Rollback이 된다.