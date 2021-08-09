foreach($nombre in $(Get-Content -Path .\lista_nombres.txt)) {
	$gender_api_result = Invoke-RestMethod -Method Get -Uri https://api.genderize.io/?name=$nombre
	$gender = Select-Object -InputObject $gender_api_result -ExpandProperty Gender
	$gender_prob = Select-Object -InputObject $gender_api_result -ExpandProperty Probability
	$country = Invoke-RestMethod -Method Get -Uri https://api.nationalize.io/?name=$nombre | Select-Object -ExpandProperty Country | Select-Object -First 1
	$country_id = Select-Object -InputObject $country -ExpandProperty country_id
	$country_prob = Select-Object -InputObject $country -ExpandProperty probability

	Write-Output "El genero de $nombre es: $gender con una probabilidad de $gender_prob"
	Write-Output "El pais de $nombre es: $country_id con una probabilidad de $country_prob"
}