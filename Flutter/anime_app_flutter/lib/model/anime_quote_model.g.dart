// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'anime_quote_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

AnimeQuoteModel _$AnimeQuoteModelFromJson(Map<String, dynamic> json) =>
    AnimeQuoteModel(
      anime: json['anime'] as String?,
      character: json['character'] as String?,
      quote: json['quote'] as String?,
    );

Map<String, dynamic> _$AnimeQuoteModelToJson(AnimeQuoteModel instance) =>
    <String, dynamic>{
      'anime': instance.anime,
      'character': instance.character,
      'quote': instance.quote,
    };
