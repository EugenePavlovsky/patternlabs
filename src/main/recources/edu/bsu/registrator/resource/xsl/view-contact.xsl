<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:variable name="context" select="dto/context"/>
  <xsl:variable name="rootUrl" select="dto/rootUrl"/>
  <xsl:variable name="synchToken" select="dto/synchronizationToken"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>Simple Registrator</title>
      </head>
      <body>
        <h1>View Contact</h1>
        <br/>
        <br/>
        <p/>
        <form action="{$context}{$rootUrl}" method="post" name="actionForm">
          <input type="hidden" name="actionName"/>
          <input type="hidden" name="synchToken" value="{$synchToken}"/>
          <xsl:apply-templates/>
          <a href="javascript:setActionName('view_contacts')">View existing Contacts</a>
          <p/>
          <a href="javascript:setActionName('index')">Return to home page</a>
        </form>
      </body>
      <script language="JavaScript" type="text/javascript">
        function setActionName(actionName)
        {
        document.actionForm.actionName.value = actionName;
        document.actionForm.submit();
        }
      </script>
    </html>
  </xsl:template>
  <xsl:template match="context"/>
  <xsl:template match="rootUrl"/>
  <xsl:template match="synchronizationToken"/>
  <xsl:template match="id"/>
  <xsl:template match="phone">
    Phone:
    <xsl:value-of select="."/>
    <p/>
  </xsl:template>
  <xsl:template match="email">
    Email:
    <xsl:value-of select="."/>
    <p/>
  </xsl:template>
  <xsl:template match="icq">
    ICQ:
    <xsl:value-of select="."/>
    <p/>
  </xsl:template>
</xsl:stylesheet>