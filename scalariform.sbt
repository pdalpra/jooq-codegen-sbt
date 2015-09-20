import scalariform.formatter.preferences._

scalariformSettings

scalariformPreferences := (
  FormattingPreferences()
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(AlignParameters, true)
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(IndentLocalDefs, true)
    .setPreference(PreserveDanglingCloseParenthesis, true)
)
