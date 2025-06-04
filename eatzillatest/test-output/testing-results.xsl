<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" indent="yes"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>TestNG Report</title>
        <style>
          body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
          }
          h1 {
            color: #333;
            text-align: center;
          }
          table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
          }
          th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
          }
          th {
            background-color: #444;
            color: white;
          }
          tr:nth-child(even) {
            background-color: #f9f9f9;
          }
          .pass {
            color: green;
            font-weight: bold;
          }
          .fail {
            color: red;
            font-weight: bold;
          }
        </style>
      </head>
      <body>
        <h1>TestNG Report</h1>
        <p>Total: <xsl:value-of select="/testng-results/@total"/> |
           Passed: <span class="pass"><xsl:value-of select="/testng-results/@passed"/></span> |
           Failed: <span class="fail"><xsl:value-of select="/testng-results/@failed"/></span> |
           Skipped: <xsl:value-of select="/testng-results/@skipped"/></p>

        <table>
          <tr>
            <th>Class</th>
            <th>Test Method</th>
            <th>Status</th>
            <th>Duration (ms)</th>
          </tr>
          <xsl:for-each select="/testng-results/suite/test/class/test-method">
            <tr>
              <td><xsl:value-of select="../../@name"/></td>
              <td><xsl:value-of select="@name"/></td>
              <td>
                <span class="{translate(@status, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')}">
                  <xsl:value-of select="@status"/>
                </span>
              </td>
              <td><xsl:value-of select="@duration-ms"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
