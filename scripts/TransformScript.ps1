<# ======================================================================================================================================
Copyright (c) DevOps-Automation.net

MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

======================================================================================================================================#>

<# ------------------------------------------------------------------------------------------------------------------------------------
DESCRIPTION:
 Helper script to replace placeholders in config file.
---------------------------------------------------------------------------------------------------------------------------------------#>

param(
    [Parameter(Mandatory=$true)]
    [String] $workspaceDir,

    [Parameter(Mandatory=$true)]
    [String] $secretRegCode
)

#-------------------------------------------------------
# Replaces placeholders from config file.
#-------------------------------------------------------
function Replace-Placeholders([String] $fileText) {
    $fileText = $fileText.Replace("%github_token%", $secretRegCode)
    $fileText
}

# Read config.properties file content.
Write-Host "Reading config file -> $configFile ..."
[String] $configFile = [System.IO.Path]::Combine($workspaceDir, "GitHubRestAPIAutomation_Project", "config.properties")
$utf8Enc = [System.Text.UTF8Encoding]::new($false, $true)
$fileContent = [System.IO.File]::ReadAllText($configFile, $utf8Enc)

# Replace placeholders from config.properties.
Write-Host "Tranforming config file -> $configFile ..."
$fileContent = Replace-Placeholders -fileText $fileContent
Write-Host "Done transforming config file."

# Write updated content to config.properties.
Write-Host "Writing transformed file content to -> $configFile ..."
[System.IO.File]::WriteAllText($configFile, $fileContent)
Write-Host "Done writing transformed file content."