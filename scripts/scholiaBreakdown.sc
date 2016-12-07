import scala.io.Source

@main
def scholiaBreakdown (folder: String) {

  val nameFile = Source.fromFile(folder).getLines.toVector

  for (file <- nameFile) {

    val origFile = Source.fromFile("TableFolder/" + file).getLines.toVector

    val totalScholia: Double = origFile.size

    val mainScholia: Double = origFile.filter(_.contains("6.msA.")).size

    val interlinear: Double = origFile.filter(_.contains("6.msAil.")).size

    val intermarg: Double = origFile.filter(_.contains("6.msAim.")).size

    val interior: Double = origFile.filter(_.contains("6.msAint.")).size

    val ext: Double = origFile.filter(_.contains("6.msAext.")).size

    require (totalScholia == mainScholia + interlinear + intermarg + interior + ext)

    println("\nThis is scholia set " + file + " contains:\nmain scholia: "
      + mainScholia + " (" + (mainScholia / totalScholia) + ")\nintermarginal scholia: "
       + intermarg + " (" + (intermarg / totalScholia) + ")\ninterior scholia: "
       + interior + " (" + (interior / totalScholia) + ")\ninterlinear scholia: "
       + interlinear + " (" + (interlinear / totalScholia) + ")\nexterior scholia: "
       + ext + " (" + (ext / totalScholia) + ")\nIn total there are " + totalScholia + " scholia.")
  }
}
