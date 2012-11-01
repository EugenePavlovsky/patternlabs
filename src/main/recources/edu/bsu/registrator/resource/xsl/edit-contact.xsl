<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:variable name="context" select="dto/context"/>
  <xsl:variable name="rootUrl" select="dto/rootUrl"/>
  <xsl:variable name="synchToken" select="dto/synchronizationToken"/>
  <xsl:variable name="id" select="dto/id"/>
  <xsl:variable name="phone" select="dto/phone"/>
  <xsl:variable name="email" select="dto/email"/>
  <xsl:variable name="icq" select="dto/icq"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>Simple Registrator</title>
      </head>
      <body>
        <h1>Edit Contact</h1>
        <br/>
        <br/>
        <p/>
        <form action="{$context}{$rootUrl}" method="post" name="actionForm">
          <input type="hidden" name="actionName"/>
          <input type="hidden" name="synchToken" value="{$synchToken}"/>
          <input type="hidden" name="contactId" value="{$id}"/>
          Phone:
          <input type="text" name="phone" value="{$phone}"/>
          <p/>
          Email:
          <input type="text" name="email" value="{$email}"/>
          <p/>
          ICQ:
          <input type="text" name="icq" value="{$icq}"/>
          <p/>
          <a href="javascript:setActionName('save_contact')">Save</a>
          <p/>
          <a href="javascript:setActionName('index')">Cancel</a>
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
</xsl:stylesheet>