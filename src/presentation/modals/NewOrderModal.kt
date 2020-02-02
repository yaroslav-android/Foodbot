package team.uptech.food.bot.presentation.modals

class NewOrderModal : BaseModal() {
  private fun getNewOrderModal(triggerId: String): String {
    // TODO: consider generate id for each user to identify presentation.modals by callback_id?
    return """
            {
                "trigger_id": "$triggerId",
                "view": {
            	    "type": "modal",
                    "callback_id": "order_initiation",
            	    "title": {
            		    "type": "plain_text",
            		    "text": "New Order :stew:",
            		    "emoji": true
            	    },
            	    "submit": {
            		    "type": "plain_text",
            		    "text": "Schedule",
            		    "emoji": true
            	    },
            	    "close": {
            		    "type": "plain_text",
            		    "text": "Cancel",
            		    "emoji": true
            	    },
            	    "blocks": [${getNewOrderModalBlocks()}]
                }
            }
        """.trimIndent()
  }

  private fun getNewOrderModalBlocks(): String {
    return """
            {
                "type": "input",
                "block_id": "amount_of_ppl_block",
                "element": {
                    "type": "plain_text_input",
                    "action_id": "amount_of_ppl_block_input",
                    "placeholder": {
                        "type": "plain_text",
                        "text": "7",
                        "emoji": false
                    }
                },
                "label": {
                    "type": "plain_text",
                    "text": "Amount of people",
                    "emoji": false
                }
            },
            {
                "type": "input",
                "block_id": "time_picker_block",
                "element": {
                    "type": "plain_text_input",
                    "action_id": "time_picker_block_input",
                    "placeholder": {
                        "type": "plain_text",
                        "text": "12:00 pm",
                        "emoji": false
                    }
                },
                "label": {
                    "type": "plain_text",
                    "text": "Pick time",
                    "emoji": false
                }
            },
            {
                "type": "input",
                "block_id": "menu_link_block",
                "element": {
                    "type": "plain_text_input",
                    "action_id": "menu_link_block_input",
                    "placeholder": {
                        "type": "plain_text",
                        "text": "bit.ly/Y6sy3y",
                        "emoji": false
                    }
                },
                "label": {
                    "type": "plain_text",
                    "text": "Menu",
                    "emoji": false
                },
                "optional": true
            },
            {
                "type": "input",
                "block_id": "delivery_fee_block",
                "element": {
                    "type": "plain_text_input",
                    "action_id": "delivery_fee_block_input",
                    "placeholder": {
                        "type": "plain_text",
                        "text": "30 uah",
                        "emoji": false
                    }
                },
                "label": {
                    "type": "plain_text",
                    "text": "Delivery fee",
                    "emoji": false
                },
                "optional": true
            },
            {
                "type": "input",
                "block_id": "card_number_block",
                "element": {
                    "type": "plain_text_input",
                    "action_id": "card_number_block_input",
                    "placeholder": {
                        "type": "plain_text",
                        "text": "5175 0000 0000 0000",
                        "emoji": false
                    }
                },
                "label": {
                    "type": "plain_text",
                    "text": "Credit card (refund)",
                    "emoji": false
                }
            }
        """.trimIndent()
  }

  override fun assemble(triggerId: String): String {
    return getNewOrderModal(triggerId)
  }
}