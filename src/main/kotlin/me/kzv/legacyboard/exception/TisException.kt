package me.kzv.legacyboard.exception

import java.lang.RuntimeException

class TisException(message: String) : RuntimeException(message) // unckecked 예외 - Rollback이 된다.