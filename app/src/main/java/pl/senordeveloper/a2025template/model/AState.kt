package pl.senordeveloper.a2025template.model

interface AModel

interface ALambdas<AModel>

data class AState<M: AModel, L: ALambdas<M>>(
    val model: M,
    val lambdas: L
)