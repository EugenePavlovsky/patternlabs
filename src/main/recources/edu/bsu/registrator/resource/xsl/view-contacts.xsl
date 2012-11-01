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
        <h1>View existing Contacts</h1>
        <br/>
        <br/>
        <p/>
        <form action="{$context}{$rootUrl}" method="post" name="actionForm">
          <input type="hidden" name="actionName"/>
          <input type="hidden" name="contactId"/>
          <input type="hidden" name="synchToken" value="{$synchToken}"/>
          <xsl:apply-templates select="dto/contactDTOs"/>
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

        function setContactId(contactId)
        {
        document.actionForm.contactId.value = contactId;
        }
      </script>
    </html>
  </xsl:template>
  <xsl:template match="contactDTOs">
    <xsl:for-each select="contactDTO">
      Phone:<xsl:value-of select="phone"/>
      <br/>
      Email:<xsl:value-of select="email"/>
      <br/>
      ICQ:<xsl:value-of select="icq"/>
      <br/>
      <a>
        <xsl:attribute name="href">
          javascript:setContactId(
          <xsl:value-of select="id"/>
          );setActionName('view_contact')
        </xsl:attribute>
        View
      </a>
      &#160;|&#160;
      <a>
        <xsl:attribute name="href">
          javascript:setContactId(
          <xsl:value-of select="id"/>
          );setActionName('edit_contact')
        </xsl:attribute>
        Edit
      </a>
      &#160;|&#160;
      <a>
        <xsl:attribute name="href">
          javascript:setContactId(
          <xsl:value-of select="id"/>
          );setActionName('delete_contact')
        </xsl:attribute>
        Delete
      </a>
      <p/>
      <p/>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>